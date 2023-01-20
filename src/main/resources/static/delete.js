let formDelete = document.forms["formDeletePerson"];
deleteUser();

 async function deleteForm(id) {
   const modal = new bootstrap.Modal(document.querySelector('#deleteModal'));
    await openAndFillInTheModal(formDelete, modal, id);
    switch (formDelete.roles.value) {
        case '1':
            formDelete.roles.value = 'USER';
            break;
        case '2':
            formDelete.roles.value = 'ADMIN';
            break;
    }
}

function deleteUser() {
    formDelete .addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8080/api/persons/" + formDelete .id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#deleteFormCloseButton').click();
            findAll();
        });
    });
}

