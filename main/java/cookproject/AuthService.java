package cookproject;

public class AuthService {
    private UserRepository userRepository;

    public AuthService() {
        userRepository = new UserRepository();
    }

     // ✅ هذا الكونستركتر يستخدم أثناء الاختبار فقط
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "user_not_found";
        } else if (!user.getPassword().equals(password)) {
            return "invalid_password";
        } else {
            return "success:" + user.getRole();
        }
    }
}
