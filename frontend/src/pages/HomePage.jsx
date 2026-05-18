import { Link } from 'react-router-dom'

export default function HomePage() {
  return (
    <main className="container">
      <section className="hero hero-gradient">
        <span className="badge">Campus Hiring Platform</span>
        <h1>Smart Student Placement Portal</h1>
        <p>Students apply, recruiters hire, and admins monitor everything from one clean and modern workspace.</p>
        <div className="row">
          <Link to="/register" className="link-btn">Get Started</Link>
          <Link to="/login" className="link-btn secondary">Sign In</Link>
        </div>
      </section>

      <section className="grid cols-3 metrics">
        <article className="card"><h3>Faster Hiring</h3><p>Streamlined workflows reduce manual work and speed up selection.</p></article>
        <article className="card"><h3>Unified Profiles</h3><p>Students maintain a single profile with resume and skill highlights.</p></article>
        <article className="card"><h3>Live Insights</h3><p>Track job demand and skill trends through in-app analytics.</p></article>
      </section>

      <section className="grid cols-3 feature-grid">
        <article className="card"><h3>For Students</h3><p>Build your profile, upload resumes, apply to opportunities, and track outcomes.</p></article>
        <article className="card"><h3>For Recruiters</h3><p>Post openings, review applicants, shortlist talent, and download resumes quickly.</p></article>
        <article className="card"><h3>For Admins</h3><p>Manage students and recruiters while monitoring placement trends and activity.</p></article>
      </section>
    </main>
  )
}
