package com.esraa.hp.realmproject;


import android.content.Context;



import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class RealmService {
    Realm mRealm;
String comment="اول حاجة هعمل الداتابيز بتاعتى RealmConfiguration و هديلها اسم و رقم version و هقوله build" +
        "و جوه الكونستراكتور بتاع RealmService هنادى على initRealm اللى بيعمل الداتابيز و اعمل من Realm اوبجكت " +
        " نفس ال operations اللى في ال contact application الفرق بس ان الداتا اللى راجعة من realm " +
        "هيبقي نوعها RealmResults<Contact";

    public void initRealm(Context context){
        Realm.init(context);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("contact.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public RealmService(Context context) {
        initRealm(context);
        mRealm = Realm.getDefaultInstance();

    }

    public void addContact(final String name,final String phone) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                Contact contact = realm.createObject(Contact.class,realm.where(Contact.class).findAll().size());
                contact.setName(name);
                contact.setPhone(phone);
            }
        });
    }


    public RealmResults<Contact> getAllContacts() {
        return mRealm.where(Contact.class).findAll();
    }


    public Contact getContact(int id){
        return mRealm.where(Contact.class).equalTo("id",id).findFirst();
    }

    public void updateContact(final Contact contact){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(contact);
            }
        });
    }

    public void deleteContact(final int id){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Contact.class).equalTo("id", id).findAll().deleteAllFromRealm();

            }
        });
    }




}
