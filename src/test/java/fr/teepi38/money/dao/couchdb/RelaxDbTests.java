package fr.teepi38.money.dao.couchdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RelaxDbTests {
    private RelaxDb db;

    @BeforeEach
    protected void onSetUp() throws Exception {
        try {
            db = RelaxDb.builder()
                .url(new URI("http://localhost:5984/budget"))
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
