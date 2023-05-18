

function delete_user_by_id(id) {
    console.log(id)
    fetch('/admin/panel/users/delete?id=' + id,  {
        method: 'DELETE'
    });
}
