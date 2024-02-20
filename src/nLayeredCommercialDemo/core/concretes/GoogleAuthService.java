package nLayeredCommercialDemo.core.concretes;

import nLayeredCommercialDemo.core.abstracts.AuthService;

public
class GoogleAuthService implements AuthService {
    @Override
    public
    void loginWithExternalService (String externalService) {
        // google ile kayıt olma kodları
        System.out.println ("kullanıcı "+externalService +" ile giriş yaptınız." );
    }
}
