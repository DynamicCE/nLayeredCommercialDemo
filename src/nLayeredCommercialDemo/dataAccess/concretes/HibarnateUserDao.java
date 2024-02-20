package nLayeredCommercialDemo.dataAccess.concretes;

import nLayeredCommercialDemo.dataAccess.abstracts.UserDao;

public
class HibarnateUserDao implements UserDao {
    @Override
    public
    boolean isMailExist ( String email ) {
        return false;  // Veritabanında e-posta adresinin olup olmadığını kontrol eden sorgu.
    }
}
