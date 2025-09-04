// js/session.js  — manejo simple de sesión en localStorage
const KEY = 'eva3User';

// --- getters/setters ---
export function setUser(u) {
  // u: { id:number, nombre:string, rol:'ADMIN'|'PROFESOR'|'ALUMNO' }
  localStorage.setItem(KEY, JSON.stringify(u));
}

export function getUser() {
  try { return JSON.parse(localStorage.getItem(KEY)) || null; }
  catch { return null; }
}

export function clearUser() {
  localStorage.removeItem(KEY);
}

// --- guards (redireccionan a /login.html si no cumple) ---
export function requireUser() {
  const u = getUser();
  if (!u) {
    location.href = 'login.html';
    throw new Error('No autorizado');
  }
  return u;
}

export function requireRole(role) {
  const u = requireUser();
  if (u.rol !== role) {
    location.href = 'login.html';
    throw new Error('Rol no autorizado');
  }
  return u;
}

export function requireAnyRole(...roles) {
  const u = requireUser();
  if (!roles.includes(u.rol)) {
    location.href = 'login.html';
    throw new Error('Rol no autorizado');
  }
  return u;
}

// --- logout ---
export function logout() {
  clearUser();
  location.href = 'login.html';
}
