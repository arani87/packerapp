package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.InputObject;
import net.bytebuddy.description.method.ParameterList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class PackerTest {

    @Spy
    private List<InputObject> mockArrayList = new ArrayList<>();

    @Test
    public void testPackPositiveCase() throws APIException {
        System.out.print(Packer.pack("src/main/resources/input.txt"));
    }

    @Test(expected = APIException.class)
    public void testConstraintMaxPackageWeightExceeded() throws APIException {
        InputObject dummyObject = new InputObject();
        dummyObject.setPackageWeightCapacity(101d);
        mockArrayList.add(dummyObject);
        Packer.validateConstraints(mockArrayList);

    }
}
