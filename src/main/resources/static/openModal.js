async function openAndFillInTheModal(form, modal, id){
    modal.show();
    let person = await getModalPerson(id);
    form.id.value = person.id;
    form.name.value = person.name;
    form.surname.value = person.surname;
    form.login.value = person.login;
    form.roles.value = person.roles[0].id;
}