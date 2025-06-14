document.addEventListener('DOMContentLoaded', function () {
  const habitForm = document.getElementById('habit-form');
  const habitsList = document.getElementById('habits-list');

  function renderHabits(habits) {
    habitsList.innerHTML = '';
    if (!habits.length) {
      habitsList.innerHTML = '<p>No habits found.</p>';
      return;
    }
    habits.forEach(habit => {
      const card = document.createElement('div');
      card.className = 'habit-card';
      card.innerHTML = `
        <div class="habit-info">
          <h3>${habit.habitName}</h3>
          <p>${habit.habitDescription || ''}</p>
          <span class="status ${habit.habitStatus}">${habit.habitStatus}</span>
        </div>
        <div class="habit-actions">
          <button class="status-btn">Mark as ${habit.habitStatus === 'completed' ? 'Pending' : 'Completed'}</button>
          <button class="delete-btn">Delete</button>
        </div>
      `;
      // Update status
      card.querySelector('.status-btn').onclick = () => {
        updateHabitStatus(habit.id, habit.habitStatus === 'completed' ? 'pending' : 'completed');
      };
      // Delete
      card.querySelector('.delete-btn').onclick = () => {
        deleteHabit(habit.id);
      };
      habitsList.appendChild(card);
    });
  }

  async function fetchHabits() {
    habitsList.innerHTML = '<p>Loading...</p>';
    const res = await fetch('/api/habits-tracker/getAllHabits');
    const data = await res.json();
    renderHabits(data);
  }

  habitForm.onsubmit = async function (e) {
    e.preventDefault();
    const habitName = document.getElementById('habitName').value.trim();
    const habitDescription = document.getElementById('habitDescription').value.trim();
    if (!habitName) return;
    await fetch('/api/habits-tracker/save', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ habitName, habitDescription, habitStatus: 'pending' })
    });
    habitForm.reset();
    fetchHabits();
  };

  async function updateHabitStatus(id, status) {
    await fetch('/api/habits-tracker/updateHabitStatus', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id, habitStatus: status })
    });
    fetchHabits();
  }

  async function deleteHabit(id) {
    await fetch('/api/habits-tracker/deleteHabit', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id })
    });
    fetchHabits();
  }

  fetchHabits();
});

