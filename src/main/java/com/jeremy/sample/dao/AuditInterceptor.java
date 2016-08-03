package com.jeremy.sample.dao;

import com.jeremy.sample.domain.entity.AbstractAuditable;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeremy Yang on 22/12/2015.
 */
public class AuditInterceptor extends EmptyInterceptor
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditInterceptor.class);
    private int updates;
    private int creates;
    private int loads;

    public void onDelete(Object entity,
                         Serializable id,
                         Object[] state,
                         String[] propertyNames,
                         Type[] types) {
        // do nothing
    }

    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) {

        boolean iUpdatedAtSetup = false;
        boolean iUpdatedBySetup = false;

        if ( entity instanceof AbstractAuditable) {
            updates++;
            for ( int i=0; i < propertyNames.length; i++ ) {
                if ( "updatedAt".equals( propertyNames[i] ) ) {
                    currentState[i] = new Date();
                    iUpdatedAtSetup = true;
                }
                if ( "updatedBy".equals( propertyNames[i] ) ) {
                    currentState[i] = "rabbitmq-service-sample";
                    iUpdatedBySetup = true;
                }
            }
            if (iUpdatedAtSetup && iUpdatedBySetup) {
                return true;
            }
        }
        return false;
    }

    public boolean onLoad(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        if ( entity instanceof AbstractAuditable ) {
            loads++;
        }
        return false;
    }

    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {

        boolean iCreatedAtSetup = false;
        boolean iCreatedBySetup = false;

        if ( entity instanceof AbstractAuditable ) {
            creates++;
            for ( int i=0; i<propertyNames.length; i++ ) {
                if ( "createdAt".equals( propertyNames[i] ) ) {
                    state[i] = new Date();
                    iCreatedAtSetup = true;
                }
                if ("createdBy".equals( propertyNames[i] )) {
                    state[i] = "rabbitmq-service-sample";
                    iCreatedBySetup = true;
                }
            }
            if (iCreatedAtSetup && iCreatedBySetup)
            {
                return  true;
            }
        }
        return false;
    }

    public void afterTransactionCompletion(Transaction tx) {
        if ( tx.wasCommitted() ) {
            LOGGER.debug("Creations: " + creates + ", Updates: " + updates + "Loads: " + loads + "in current transaction.");
        }
        updates=0;
        creates=0;
        loads=0;
    }

}
