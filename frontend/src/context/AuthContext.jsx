import { createContext, useContext, useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import client from '../api/client'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [auth, setAuth] = useState({ token: localStorage.getItem('token'), user: null, theme: localStorage.getItem('theme') || 'light' })

  useEffect(() => {
    document.documentElement.setAttribute('data-theme', auth.theme)
    localStorage.setItem('theme', auth.theme)
  }, [auth.theme])

  useEffect(() => {
    if (!auth.token) return
    client.get('/auth/me').then((res) => {
      setAuth((prev) => ({ ...prev, user: res.data.data }))
    }).catch(() => logout())
  }, [auth.token])

  const login = async (email, password) => {
    const res = await client.post('/auth/login', { email, password })
    localStorage.setItem('token', res.data.data.token)
    setAuth((prev) => ({ ...prev, token: res.data.data.token, user: res.data.data }))
    toast.success('Login successful')
    return res.data.data
  }

  const register = async (role, payload) => {
    const endpoint = role === 'RECRUITER' ? '/auth/register/recruiter' : '/auth/register/student'
    const res = await client.post(endpoint, payload)
    localStorage.setItem('token', res.data.data.token)
    setAuth((prev) => ({ ...prev, token: res.data.data.token, user: res.data.data }))
    toast.success('Registered successfully')
    return res.data.data
  }

  const logout = () => {
    localStorage.removeItem('token')
    setAuth((prev) => ({ ...prev, token: null, user: null }))
  }

  const toggleTheme = () => {
    setAuth((prev) => ({ ...prev, theme: prev.theme === 'light' ? 'dark' : 'light' }))
  }

  return <AuthContext.Provider value={{ ...auth, login, register, logout, toggleTheme }}>{children}</AuthContext.Provider>
}

export const useAuth = () => useContext(AuthContext)
