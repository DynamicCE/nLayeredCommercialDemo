package nLayeredCommercialDemo.business.abstracts;

import nLayeredCommercialDemo.core.abstracts.AuthService;

public
interface UserService {
    void register ( String password, String nick, String email );

    boolean validateEmail ( String email );

    boolean validatePassword ( String password );

    boolean validateNick ( String nick );

    boolean checkMailExist ( String email );

    void sendVerificationMail ( String email );

    void completeRegistration ( String token );

    boolean isValidToken ( String token );

    void login ( String externalService);


}
