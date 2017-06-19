//package io.skysail.core.um.domain;
//
//import javax.validation.constraints.Size;
//
//import io.skysail.core.Entity;
//import io.skysail.core.html.Field;
//import io.skysail.core.html.InputType;
//
//
//public class CredentialsOld implements ScalaEntity[String] {
//
//    @Size(min = 3, message = "Username must have at least three characters")
//    @Field
//    private String username;
//
//    @Size(min = 3, message = "Password must have at least three characters")
//    @Field(inputType = InputType.PASSWORD)
//    private String password;
//
//    public CredentialsOld() {
//    }
//
//    public CredentialsOld(String username, String password) {
//        this.username = username.replace("@", "&#64;");
//        this.password = password;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getId() {
//        return null;
//    }
//
//}
