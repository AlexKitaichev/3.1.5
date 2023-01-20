Principal();

function Principal() {
    fetch("http://localhost:8080/api/principal")
        .then(res => res.json())
        .then(data => {
            $('#headerPersonName').append(data.login);
            let roles = data.roles.map(e => e.role);
            $('#principalRole').append(roles);
            let person = `$(
                <tr>
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.surname}</td>
                    <td>${data.login}</td>
                    <td>${roles}</td>
                </tr>)`;
            $('#personTable').append(person);
        })
}