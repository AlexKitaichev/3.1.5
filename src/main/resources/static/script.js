const personsFetchUrl = "http://localhost:8080/api/persons";
const table = $('#personsTable');
findAll();
function findAll() {
    table.empty()
    fetch(personsFetchUrl)
        .then(res => res.json())
        .then(data => {
            data.forEach(person => {

                let personsTable = `$(
                        <tr>
                            <td>${person.id}</td>
                            <td>${person.name}</td>
                            <td>${person.surname}</td>
                            <td>${person.login}</td>
                            <td>${person.roles.map(e => e.role)}</td>
                            <td>
                                <button type="button" class="btn btn-info"
                                data-bs-toogle="modal"
                                data-bs-target="#editModal"
                                onclick="editForm(${person.id})">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" 
                                data-toggle="modal"
                                data-bs-target="#deleteModal"
                                onclick="deleteForm(${person.id})">Delete</button>
                            </td>
                        </tr>)`;
                table.append(personsTable);
            })
        })
}

