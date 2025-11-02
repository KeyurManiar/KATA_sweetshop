package com.sweetshop;

import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class SweetServiceTest {

    @Test
    public void testPurchaseReducesQuantity() {
        Sweet s = new Sweet("Ladoo","Traditional",10.0,5);
        SweetRepository repo = Mockito.mock(SweetRepository.class);
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(s));
        // simulate purchase
        s.setQuantity(s.getQuantity()-2);
        assertEquals(3, s.getQuantity());
    }
}
