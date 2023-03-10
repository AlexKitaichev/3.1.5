let formNew = document.forms["formNewPerson"];
const newPersonsFetchUrl = "http://localhost:8080/api/admin/users";
addUser();

function addUser() {
    formNew.addEventListener("submit", ev => {
        ev.preventDefault();
        let newUserRoles = [];
        for (let i = 0; i < formNew.roles.options.length; i++) {
            if (formNew.roles.options[i].selected) newUserRoles.push({
                id: formNew.roles.value,
                name: "ROLE_" + formNew.roles.options[i].text
            });
        }
        fetch(newPersonsFetchUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: formNew.name.value,
                surname: formNew.surname.value,
                login: formNew.login.value,
                password: formNew.password.value,
                roles: newUserRoles
            })
        }).then(() => {
            formNew.reset();
            findAll();
            $('#home-tab').click();
        });
    });
}