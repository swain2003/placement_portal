import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { useAuth } from '../context/AuthContext'

export default function RegisterPage() {
  const { register } = useAuth()
  const navigate = useNavigate()
  const [role, setRole] = useState('STUDENT')
  const [form, setForm] = useState({ fullName: '', email: '', password: '', companyName: '' })

  const submit = async (e) => {
    e.preventDefault()
    try {
      const data = await register(role, form)
      navigate(data.role === 'RECRUITER' ? '/recruiter' : '/student')
    } catch (err) {
      toast.error(err.response?.data?.message || 'Registration failed')
    }
  }

  return (
    <main className="container small">
      <h2>Register</h2>
      <form className="card" onSubmit={submit}>
        <select value={role} onChange={(e) => setRole(e.target.value)}>
          <option value="STUDENT">Student</option>
          <option value="RECRUITER">Recruiter</option>
        </select>
        <input placeholder="Full name" value={form.fullName} onChange={(e) => setForm({ ...form, fullName: e.target.value })} required />
        <input placeholder="Email" type="email" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} required />
        <input placeholder="Password" type="password" minLength={6} value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} required />
        {role === 'RECRUITER' && <input placeholder="Company name" value={form.companyName} onChange={(e) => setForm({ ...form, companyName: e.target.value })} required />}
        <button type="submit">Create Account</button>
      </form>
    </main>
  )
}
