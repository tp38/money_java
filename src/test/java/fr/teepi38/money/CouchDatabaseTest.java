package fr.teepi38.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;

import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;


public class CouchDatabaseTest extends AssertJSwingJUnitTestCase {
    private CouchDatabase db;

    @Override
    protected void onSetUp() throws Exception {
        try {
            db = CouchDatabase.builder()
                .url(new URI("http://localhost:5984/budget"))
                .repl_init(null)
                .repl_save(null)
                .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    @Test
    public void replication_doc_default_to_null() {
        assertNull( db.repl_init() );
        assertNull( db.repl_save() );
    }
    
    @Test
    public void have_a_summary_method() {
        assertEquals("localhost (budget)", db.summary() );
    }

    @Test
    public void have_a_get_url_method() {
        assertEquals("http://localhost:5984/budget", db.url().toString() );
    }
}
