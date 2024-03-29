import {SystemUserDTO} from "../dto/SystemUserDTO";

export function getSession() : SystemUserDTO | undefined {
  try {
    const serializedState = localStorage.getItem('session');
    if (serializedState === null) {
      return undefined;
    }
    return JSON.parse(serializedState) as SystemUserDTO;
  } catch (err) {
    return undefined;
  }
}

export function saveSession(state: SystemUserDTO) {
  try {
    const serializedState = JSON.stringify(state);
    localStorage.setItem('session', serializedState);
  } catch (err) {
    console.error(err);
  }
}

export function invalidateSession() {
  localStorage.removeItem('session');
}

export function getToken() {
  try {
    const token = localStorage.getItem('token');
    if (token === null) {
      return '';
    }
    return token;
  } catch (err) {
    return '';
  }
}

export function saveToken(token: string) {
  try {
    localStorage.setItem('token', token);
  }
  catch (err) {
    console.error(err);
  }
}

export function invalidateToken() {
  localStorage.removeItem('token');
}
