async function getModalPerson(id) {
    let url = "http://localhost:8080/api/admin/users/" + id;
    let response = await fetch(url);
    return await response.json();
}