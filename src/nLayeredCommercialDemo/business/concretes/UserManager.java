package nLayeredCommercialDemo.business.concretes;

import nLayeredCommercialDemo.business.abstracts.UserService;
import nLayeredCommercialDemo.dataAccess.abstracts.UserDao;
import nLayeredCommercialDemo.dataAccess.concretes.HibarnateUserDao;
import nLayeredCommercialDemo.entities.concretes.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public
class UserManager implements UserService {

    // dependency injection için
    // 1- interface'i tanımla
    // 2- constructor oluştur
    private UserDao userDao;

    public
    UserManager ( UserDao userDao ) {
        this.userDao = userDao;
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
        if (checkMail ( email )) {
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
    boolean checkMail ( String email ) {
//        if (!userDao.isMailExist ( email )) {
//            System.out.println ( "Bu email ile zaten kayıtlısınız." );
//        }
        return userDao.isMailExist ( email );
    }

    public
    void sendVerificationMail ( String email ) {
        System.out.println ( email + " adresine doğrulama e-postası gönderildi." );
        // doğrulama maili gönderecek kod

    }

}
/*  Regex Kullanımı
 *   bir pattern alırız ya da yazarız (pattern istediğimiz formatı belirtir.)
 *   Pattern türünde değişken oluştururuz ve buna Pattern.compile(patternimiz) 'i atarız
 *   Matcher nesnesi de oluşturup buna da pattern nesnesinin .matcher(patternimiz) metodunu atarız
 *   matcher.matches() ile de kontrol edilir
 * */