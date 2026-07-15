# Job_Portal
Job Portal built with Java, Spring Boot Microservices, Spring Security, JWT, MySQL, API Gateway, Eureka, and Docker.

## API Endpoints

### POST /users/register
Registers a new user by storing user details securely with an encrypted password.

### POST /users/login
Authenticates the user and returns a JWT token for accessing protected APIs.

### POST /users/generateOTP
Generates and sends a One-Time Password (OTP) to the registered email for password recovery.

### POST /users/verifyEmail
Verifies the OTP entered by the user before allowing a password reset.

### POST /users/resetPassword
Allows users to create a new password after successful OTP verification.

### PATCH /users/changePassword
Updates the current password after validating the existing password.

### GET /users/profile
Returns the profile details of the currently authenticated user.

### PUT /users/putChangeProfile
Replaces the authenticated user's profile information with the provided data.

### PATCH /users/PatchUpdateProfile
Updates only the required profile fields without replacing the entire profile.

### GET /users/{userMail}
Returns the details of a specific user. Accessible only by an ADMIN.

### GET /users/getusers
Returns a paginated list of all registered users. Accessible only by an ADMIN.

### DELETE /users/{userMail}
Deletes the authenticated user's account from the system.

| Endpoint        | Purpose                       | Authentication  |
| --------------- | ----------------------------- | --------------  |
| Register        | Create a new user account     | ❌ No           |
| Login           | Generate JWT token            | ❌ No           |
| Generate OTP    | Send OTP to email             | ❌ No           |
| Verify Email    | Verify OTP                    | ❌ No           |
| Reset Password  | Reset forgotten password      | ❌ No           |
| Change Password | Update password               | ✅ User         |
| Get Profile     | View logged-in user profile   | ✅ User         |
| Update Profile  | Modify logged-in user profile | ✅ User         |
| Get User        | View a specific user          | ✅ Admin        |
| Get All Users   | View all users                | ✅ Admin        |
| Delete User     | Delete logged-in account      | ✅ User         |
