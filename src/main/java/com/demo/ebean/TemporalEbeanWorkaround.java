package com.demo.ebean;

import io.ebean.Ebean;
import io.ebeaninternal.server.core.DefaultServer;
import io.ebeaninternal.server.transaction.JtaTransaction;
import lombok.SneakyThrows;

import javax.naming.InitialContext;
import javax.transaction.Synchronization;
import javax.transaction.TransactionSynchronizationRegistry;

public class TemporalEbeanWorkaround {

    @SneakyThrows
    public static void apply() {
        // TODO un comment this to make ebean release the connection after the JTA transaction is completed.

        DefaultServer ds = (DefaultServer) Ebean.getDefaultServer();
        JtaTransaction cst = (JtaTransaction) ds.getCurrentServerTransaction();
        TransactionSynchronizationRegistry tsr = (TransactionSynchronizationRegistry) new InitialContext().lookup("java:comp/TransactionSynchronizationRegistry");

        tsr.registerInterposedSynchronization( new Synchronization() {
            public void beforeCompletion() { }
            @SneakyThrows
            public void afterCompletion(int status) {
                cst.getInternalConnection().close();
            }
        });

    }
}
