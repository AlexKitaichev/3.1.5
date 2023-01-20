async function getModalPerson(id) {
    let url = "http://localhost:8080/api/persons/" + id;
    let response = await fetch(url);
    return await response.json();
}