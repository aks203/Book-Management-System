package com.aks.DAO;

import com.aks.Entity.TokenDetail;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDAOImpl implements TokenDAO {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * @param token_detail
     * return id of token
     */
    @Override
    public int saveToken(TokenDetail token_detail) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query q=currentSession.createQuery("delete from TokenDetail where user_id= :id");
        q.setParameter("id", token_detail.getUser_id());
        q.executeUpdate();
        return (Integer)currentSession.save(token_detail);
    }

    /**
     * @param user_id
     * @return token if present
     */
    @Override
    public String getToken(int user_id){
        Session session=sessionFactory.getCurrentSession();
        Query q=session.createQuery("select token from TokenDetail where user_id= :id");
        q.setParameter("id", user_id);
        return (String)q.uniqueResult();
    }
}
