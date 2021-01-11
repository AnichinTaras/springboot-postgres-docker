
function validateuser(flag) {

    var name = document.getElementById("user_name").value;
    var login = document.getElementById("user_login").value;
    var email = document.getElementById("user_email").value;
    var id = document.getElementById("user_idd").value;

    if (name == "" || login == "" || email == "") {
        alert("Please fill in all the fields");
        flag = false;
    }
    else {
        var numberformat = /^[0-9]+/;
        var textformat = /^[A-Za-z]+/;
        var mailformat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (name.match(textformat) && login.match(textformat) &&  email.match(mailformat)  ||
            id.match(numberformat) && name.match(textformat) && login.match(textformat) &&  email.match(mailformat) ) {
            alert("You have entered the correct data. If you have an error, then perhaps such a login is already in the system.");
            flag = true;
        }
        else {
            alert("Formats do not match!!");
            flag = false;
        }
    }
}


    function searchByLogin() {
        var login = document.getElementById("search_field").value;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var user = JSON.parse(this.responseText);
                var html = '<tr>\n' +
                    '        <th>User id</th>\n' +
                    '        <th>User name</th>\n' +
                    '        <th>User login</th>\n' +
                    '        <th>User email</th>\n' +
                    '        <th>Delete</th>\n' +
                    '    </tr>';
                html = html + '<tr><td>' + user.id + '</td>\n' +
                    '        <td>' + user.name + '</td>\n' +
                    '        <td>' + user.login + '</td>\n' +
                    '        <td>' + user.email + '</td>' +
                    '        <td><button onclick="deleteUser(' + user.id + ')">Delete</button></td></tr>';
                document.getElementById("usersList").innerHTML = html;
            }
        };
        xhttp.open("GET", "http://0.0.0.0:9000/users/findUserByLogin?login=" + login, true);
        xhttp.send();
    }

    function deleteUser(userId) {
        var xhttp = new XMLHttpRequest();
        xhttp.open("DELETE", "http://0.0.0.0:9000/users/deleteUser/" + userId, true);
        xhttp.send();
        loadUsers();
    }

    function editUserById() {

            var userId = document.getElementById("user_idd").value;
            var userName = document.getElementById("user_name").value;
            var userLogin = document.getElementById("user_login").value;
            var userEmail = document.getElementById("user_email").value;

            var url = "http://0.0.0.0:9000/users/editUser/";

            var data = {};
            data.name = userName;
            data.login = userLogin;
            data.email = userEmail;
            var json = JSON.stringify(data);

            var xhr = new XMLHttpRequest();
            xhr.open("PUT", url + +userId, true);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.onload = function () {
                var users = JSON.parse(xhr.responseText);
                if (xhr.readyState == 4 && xhr.status == "200") {
                    console.table(users);
                } else {
                    console.error(users);
                }
            }
        if (validateuser(false)) {loadUsers(); } else {
            xhr.send(json);
        }
       }



    function createUser() {
           var userName = document.getElementById("user_name").value;
           var userLogin = document.getElementById("user_login").value;
           var userEmail = document.getElementById("user_email").value;

           var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
           xmlhttp.open("POST", "http://0.0.0.0:9000/users/saveUser");
           xmlhttp.setRequestHeader("Content-Type", "application/json");


        if (validateuser(false)) {loadUsers(); } else {
            xmlhttp.send(JSON.stringify({name: userName, login: userLogin, email: userEmail}));
        }
           loadUsers();
       }


    function loadUsers() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var users = JSON.parse(this.responseText);
                var html = '<tr>\n' +
                    '        <th>User id</th>\n' +
                    '        <th>User name</th>\n' +
                    '        <th>User login</th>\n' +
                    '        <th>User email</th>\n' +
                    '        <th>Delete</th>\n' +
                    '    </tr>';
                for (var i = 0; i < users.length; i++) {
                    var user = users[i];
                    console.log(user);
                    html = html + '<tr><td>' + user.id + '</td>\n' +
                        '        <td>' + user.name + '</td>\n' +
                        '        <td>' + user.login + '</td>\n' +
                        '        <td>' + user.email + '</td>' +
                        '        <td><button onclick="deleteUser(' + user.id + ')">Delete</button></td></tr>';

                }
                document.getElementById("usersList").innerHTML = html;
            }
        };
        xhttp.open("GET", "http://0.0.0.0:9000/users/findAllUsers", true);
        xhttp.send();
    }

    loadUsers();
