package edu.sjsu.entertainmentbox.component;

import edu.sjsu.entertainmentbox.model.User;
import org.springframework.stereotype.Component;

@Component
public class LoginComponent {

    private User user;
    private Integer customerId;

    public LoginComponent() {
    }

    public LoginComponent(User user, Integer customerId) {
        this.user = user;
        this.customerId = customerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
