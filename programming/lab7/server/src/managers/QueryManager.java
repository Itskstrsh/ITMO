package managers;

public class QueryManager {
    String addUser = "INSERT INTO users(login, passwd, salt) VALUES (?, ?, ?)";
    String getSalt = "SELECT salt from users where login = ? ;";
    String addPerson = """
            INSERT INTO persons(name_person,x_coor, y_coor, height, eyecolor, haircolor,  nationality ,name_loc, x_loc, y_loc, z_loc,creationdate,user_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)
            RETURNING id;
            """;
    String deleteObject = "delete from persons where (user_id = ?) and (id = ?) returning id;";
    String updatePerson = """
            update persons
            set (name_person,x_coor, y_coor, height, eyecolor, haircolor,  nationality ,name_loc, x_loc, y_loc, z_loc,creationdate,user_id) = (?,?,?,?,?,?,?,?,?,?,?,?,?) where (userlogin=?) and (id=?) returning id;
            """;
    String addObjects = """
            select * from persons;
            """;
    String checkUser = "SELECT id FROM users where login = ?;";
    String getUserId = """
            select id from users where (login = ?) and (passwd = ?);
            """;
    String getObjects =  """
            select * from persons where user_id = ?;
            """;

}

