function createLockerRow() {
  const innerDiv = document.createElement('div');

  for (let i = 0; i < 11; i++) {
    const button = document.createElement('button');
    button.classList.add('popupBtn');
    innerDiv.appendChild(button);
  }

  return innerDiv;
}


function createLockerBox() {
  const outerDiv = document.createElement('div');

  outerDiv.appendChild(createLockerRow());
  outerDiv.appendChild(createLockerRow());

  return outerDiv;
}

function createAllLocker() {
  const outerDiv = document.createElement('div');

  outerDiv.appendChild(createLockerBox());
  outerDiv.appendChild(createLockerBox());
  outerDiv.appendChild(createLockerBox());
  outerDiv.appendChild(createLockerBox());

  return outerDiv;
}

const lockerComtainer = document.getElementById('box-wrapper');
const lockersDiv = createAllLocker();
lockerComtainer.appendChild(lockersDiv);