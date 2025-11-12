function updateBook(e) {
    e?.preventDefault();
    let id = document.getElementById("id").value;
    let title = document.getElementById("title").value;
    let author = document.getElementById("author").value;
    let personId = document.getElementById("person_id").value;
    fetch("/api/book/save", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({id, title, author, personId})
    }).then(() => location.href = "/api/book/get-all");
}

function deleteBook(e, id) {
    e?.preventDefault();
    fetch(`/api/book/${id}/delete`, {method: "DELETE"})
        .then(() => location.href = "/api/book/get-all")
}