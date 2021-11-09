package ir.mahdi.universityservice.config.security;

public class SecurityConstant {

    private SecurityConstant() {

    }


    public static String[] getPermitAllUrls() {
        return new String[]{
                "/login", "/assets", "/static**", "/home", "/signup**", "/sign-up", ":8080/user/confirm-user", "/user/confirm-user", "/user/confirm-user**", "/user/confirm-user/", "/user/confirm-user**"
        };
    }

    public static String[] getAdminPaths() {
        return new String[]{
                "/admin", "/admin/**", "/teacher/**", "/user/confirm-user", "/user/disprove"
        };
    }

    public static String[] getTeacherPaths() {
        return new String[]{
                "/teacher", "/teacher/*"
        };
    }

    public static String[] getStudentPath() {
        return new String[]{
                "/student", "/student/*"
        };
    }
}
