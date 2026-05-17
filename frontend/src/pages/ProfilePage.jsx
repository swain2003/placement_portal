import { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import client from '../api/client'
import { useAuth } from '../context/AuthContext'

export default function ProfilePage() {
  const { user } = useAuth()
  const [profile, setProfile] = useState(null)

  useEffect(() => {
    if (!user) return
    const endpoint = user.role === 'STUDENT' ? '/students/profile' : '/recruiters/profile'
    client.get(endpoint).then((res) => setProfile(res.data.data)).catch(() => toast.error('Could not load profile'))
  }, [user])

  const updateStudent = async (e) => {
    e.preventDefault()
    await client.put('/students/profile', {
      branch: profile.branch,
      cgpa: Number(profile.cgpa),
      graduationYear: Number(profile.graduationYear),
      skills: (profile.skillsRaw || '').split(',').map((s) => s.trim()).filter(Boolean)
    })
    toast.success('Profile updated')
  }

  const uploadResume = async (e) => {
    const file = e.target.files?.[0]
    if (!file) return
    const data = new FormData()
    data.append('file', file)
    await client.post('/students/resume', data)
    toast.success('Resume uploaded')
  }

  const updateRecruiter = async (e) => {
    e.preventDefault()
    await client.put('/recruiters/profile', {
      companyName: profile.companyName,
      description: profile.description,
      website: profile.website
    })
    toast.success('Profile updated')
  }

  if (!profile) return <main className="container"><p>Loading...</p></main>

  return (
    <main className="container small">
      <h2>Profile</h2>
      <form className="card" onSubmit={user.role === 'STUDENT' ? updateStudent : updateRecruiter}>
        {user.role === 'STUDENT' ? (
          <>
            <input placeholder="Branch" value={profile.branch || ''} onChange={(e) => setProfile({ ...profile, branch: e.target.value })} />
            <input placeholder="CGPA" type="number" step="0.1" value={profile.cgpa || ''} onChange={(e) => setProfile({ ...profile, cgpa: e.target.value })} />
            <input placeholder="Graduation Year" type="number" value={profile.graduationYear || ''} onChange={(e) => setProfile({ ...profile, graduationYear: e.target.value })} />
            <input placeholder="Skills comma separated" value={profile.skillsRaw || profile.skills?.join(', ') || ''} onChange={(e) => setProfile({ ...profile, skillsRaw: e.target.value })} />
            <label>Upload Resume PDF <input type="file" accept="application/pdf" onChange={uploadResume} /></label>
          </>
        ) : (
          <>
            <input placeholder="Company Name" value={profile.companyName || ''} onChange={(e) => setProfile({ ...profile, companyName: e.target.value })} />
            <textarea placeholder="Description" value={profile.description || ''} onChange={(e) => setProfile({ ...profile, description: e.target.value })} />
            <input placeholder="Website" value={profile.website || ''} onChange={(e) => setProfile({ ...profile, website: e.target.value })} />
          </>
        )}
        <button type="submit">Save</button>
      </form>
    </main>
  )
}
