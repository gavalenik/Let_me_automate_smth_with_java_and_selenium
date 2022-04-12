package pizzeria;

public class RestAndSqlJustForPractice {

//    @Test
//    public void tessMockTest() {
//        RestAssured
//                .when().get("http://1.1.1.1:8088/mockServices") //input address here
//                .then().assertThat().statusCode(200);
//    }
//
//    @Test
//    public void tessMockTest2() {
//
//        var requestBody = """
//                <soapenv:Envelope></soapenv:Envelope>"""; // your request here
//
//        RestAssured.baseURI = "http://1.1.1.1:8088"; //address
//        given().accept(ContentType.XML).and()
//                .body(requestBody)
//                .when().post("/mockServices") //service
//                .then().assertThat()
//                .statusCode(200).and()
//                .body("Envelope.Body.SomeTag", hasItems("V04XA", "V04YB")); //equalTo("V04XA") //check response body
//    }
//
//    @Test
//    public void tessMockTest3() {
//
//        var requestBody = """
//                <soapenv:Envelope></soapenv:Envelope>"""; // your request here
//
//        RestAssured.baseURI = "http://1.1.1.1:8088"; //address
//        String response = given().accept(ContentType.XML).and() // save response as a row
//                .body(requestBody)
//                .when().post("/mockServices") //service
//                .thenReturn()
//                .asString();
//
//        XmlPath xml = new XmlPath(response);
//
//        System.out.println(response);
//        System.out.println("↓##############↓");
//        System.out.println(xml.getString("Envelope.Body.SomeTag")); //print value from response via address
//    }
//
//    private static final String url = "jdbc:oracle:thin:@1.1.1.1:51521:sid"; // host and sid
//    private static final String user = "DBUser"; // user
//    private static final String password = "DBPass"; // password
//    private static Connection con;
//    private static Statement stmt;
//    private static ResultSet rs;
//
//    @Test
//    public void useSql() {
//
//        String query = "select count(*) from table";
//
//        try {
//            con = DriverManager.getConnection(url, user, password); // opening database connection to MySQL server
//            stmt = con.createStatement(); // getting Statement object to execute query
//            rs = stmt.executeQuery(query); // executing SELECT query
//
//            while (rs.next()) {
//                int count = rs.getInt(1);
//                System.out.println("Total number of books in the table : " + count);
//            }
//
//        } catch (SQLException sqlEx) {
//            sqlEx.printStackTrace();
//        } finally {
//            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
//        }
//    }
}
