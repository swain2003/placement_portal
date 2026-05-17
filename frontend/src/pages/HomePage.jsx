export default function HomePage() {
  return (
    <main className="container">
      <section className="hero">
        <h1>Smart Student Placement Portal</h1>
        <p>Students apply, recruiters hire, admins track complete placement analytics in one platform.</p>
      </section>
      <section className="grid cols-3">
        <article className="card"><h3>Student</h3><p>Build profile, upload resume, apply and track status.</p></article>
        <article className="card"><h3>Recruiter</h3><p>Post jobs, shortlist candidates, and download resumes.</p></article>
        <article className="card"><h3>Admin</h3><p>Monitor students, recruiters, jobs, and skill trends.</p></article>
      </section>
    </main>
  )
}
