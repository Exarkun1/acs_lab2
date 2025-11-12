function updatePerson(e) {
    e?.preventDefault();
    let id = document.getElementById("id").value;
    let fullName = document.getElementById("full_name").value;
    let yearOfBirth = document.getElementById("year_of_birth").value;
    let email = document.getElementById("email").value;
    fetch("/api/person/save", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({id, fullName, yearOfBirth, email})
    }).then(() => location.href = "/api/person/get-all");
}

function deletePerson(e, id) {
    e?.preventDefault();
    fetch(`/api/person/${id}/delete`, {method: "DELETE"})
        .then(() => location.href = "/api/person/get-all")
}