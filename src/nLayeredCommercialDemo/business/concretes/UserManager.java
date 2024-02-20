package nLayeredCommercialDemo.business.concretes;

import nLayeredCommercialDemo.business.abstracts.UserService;
import nLayeredCommercialDemo.core.abstracts.AuthService;
import nLayeredCommercialDemo.core.concretes.GoogleAuthService;
import nLayeredCommercialDemo.dataAccess.abstracts.UserDao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public
class UserManager implements UserService {

    // dependency injection için
    // 1- interface'i tanımla
    // 2- constructor oluştur
    private UserDao userDao;
    private AuthService authService;

    public
    UserManager ( UserDao userDao, AuthService authService ) {
        this.userDao = userDao;
        this.authService = authService;
    }

    @Override
    public
    void register ( String password, String nick, String email ) {
        if (password == null || password.isEmpty ( ) || nick == null || nick.isEmpty ( ) || email == null || email.isEmpty ( )) {
            System.out.println ( "Kullanıcı bilgilerinden birisi yanlış ama hangisi söylemem 🙈 " );
            return;
            // null olan değişken üzerinde isEmpty() çağrılırsa NullPointerException hatasına neden olur.Önce null kontrol edilmeli
        }
        if (!validateEmail ( email )) {
            System.out.println ( "Lütfen geçerli bir e-posta giriniz." );
            return;
        }
        if (!validatePassword ( password )) {
            System.out.println ( "Lütfen geçerli bi e-posta giriniz" );
            return;
        }
        if (!validateNick ( nick )) {
            System.out.println ( "nickiniz en az 2 karakterden oluşmalıdır :(" );
            return;
        }
        if (checkMailExist ( email )) {
            System.out.println ( "Bu email ile zaten kayıtlısınız." );
            return;
        }

        sendVerificationMail ( email );
    }

    @Override
    public
    boolean validateEmail ( String email ) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile ( emailPattern );

        Matcher matcher = pattern.matcher ( email );

//        if (email == null || !matcher.matches ( )) {
//            System.out.println ( "Lütfen geçerli bir e-posta giriniz." );
//        }

        return !(email == null || !matcher.matches ( ));
    }


    @Override
    public
    boolean validatePassword ( String password ) {
//        if (password.length ( ) < 6) {
//            System.out.println ( "şifre en az 6 karakterden oluşmalıdır " );
//        } register'da kullanabilmem için void olmamaları lazım, değiştiriyorum
        return password.length ( ) >= 6;
    }

    @Override
    public
    boolean validateNick ( String nick ) {
//        if (nick.length ( ) < 2) {
//            System.out.println ( "nickiniz en az 2 karakterden oluşmalıdır :(" );
//        }

        return nick.length ( ) > 2;
    }

    public
    boolean checkMailExist ( String email ) {
//        if (!userDao.isMailExist ( email )) {
//            System.out.println ( "Bu email ile zaten kayıtlısınız." );
//        }
        return userDao.isMailExist ( email );
    }

    public
    void sendVerificationMail ( String email ) {
        String verificationLink = "http://yourapp.com/verify?token=someUniqueToken";
        System.out.println ( email + " adresine doğrulama e-postası gönderildi." );
        System.out.println ( "e-postanızı doğrulamak için bu linke tıklayın " + verificationLink );
        // doğrulama maili gönderecek kod
    }

    public
    void completeRegistration ( String token ) {
        if (isValidToken ( token )) {
            // kayıt tamamlayıcı kodlar
            System.out.println ( "kaydınız başarı ile tamamlanmıştır" );
        } else {
            System.out.println ( "geçersiz doğrulama linki." );
        }
    }

    public
    boolean isValidToken ( String token ) { // doğrulama linkine tıklandığında
        // token geçerliliğini kontrol etme
        return true;
    }

    public
    void login ( String externalService ) {
        authService.loginWithExternalService ( externalService );
    }

}
/*  Regex Kullanımı
 *   bir pattern alırız ya da yazarız (pattern istediğimiz formatı belirtir.)
 *   Pattern türünde değişken oluştururuz ve buna Pattern.compile(patternimiz) 'i atarız
 *   Matcher nesnesi de oluşturup buna da pattern nesnesinin .matcher(patternimiz) metodunu atarız
 *   matcher.matches() ile de kontrol edilir
 * */