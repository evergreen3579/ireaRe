const btn = document.getElementById('box-wrapper');
const modal = document.getElementById('modalWrap');
const closeBtn = document.getElementById('closeBtn');

// btns.forEach(button => {
//   button.addEventListener('click', function() {
//     modal.style.display = "block"
//   })
// })

btn.onclick = function () {
  modal.style.display = "block"
}

closeBtn.onclick = function () {
  modal.style.display = 'none';
}

window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none"; //외부 클릭 시 숨김
  }
}