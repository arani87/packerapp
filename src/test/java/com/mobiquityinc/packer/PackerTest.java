package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class PackerTest {

    @Test
    public void testPackPositiveCase() throws APIException {
        System.out.print(Packer.pack("src/main/resources/input.txt"));
    }
}
