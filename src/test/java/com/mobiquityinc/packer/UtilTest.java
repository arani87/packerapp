package com.mobiquityinc.packer;


import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.InputObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UtilTest {

    private final Util util = new Util();

    @Test
    public void testFileReadOK() throws APIException {
        List<String> results = util.readFile("src/main/resources/input.txt");
        Assert.assertEquals(4, results.size());
    }

    @Test(expected = APIException.class)
    public void testFileReadFileNotFound() throws APIException {
        util.readFile("src/test/resources/input.txt");
    }

    @Test
    public void testparseLineToItemObject() throws APIException {
        String lineContent = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        InputObject obj = util.parseLineToItemObject(lineContent);
        Assert.assertEquals(81, obj.getPackageWeightCapacity(), 0.001);
    }

    @Test(expected = APIException.class)
    public void testparseLineToItemObjectEmptyInputLine() throws APIException {
        String lineContent = null;
        util.parseLineToItemObject(lineContent);
    }

    @Test(expected = APIException.class)
    public void testparseLineToItemObjectIncorrectInput() throws APIException {
        String lineContent = "(1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        util.parseLineToItemObject(lineContent);
    }

    @Test(expected = APIException.class)
    public void testparseLineToItemObjectIncorrectInput2() throws APIException {
        String lineContent = "8d1: (1d,53.38,€45) (2,88.f62,€98) (3,78.48,€3) (4,72.30,€76) (5#,30.18,€9) (6,46.34,€48)";
        util.parseLineToItemObject(lineContent);
    }

    @Test(expected = APIException.class)
    public void testparseLineToItemObjectIncorrectInput3() throws APIException {
        String lineContent = "81: (1d,53.38,€45) (2,88.f62,€98) (3,78.48,€3) (4,72.30,€76) (5#,30.18,€9) (6,46.34,€48)";
        util.parseLineToItemObject(lineContent);
    }

    @Test
    public void testRegex() {
        String lineContent = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        lineContent = lineContent.substring(lineContent.indexOf('('), lineContent.length());
        String content[] = lineContent.split("[)]");
        Assert.assertEquals(6, content.length);
    }

    @Test
    public void testgetListOfItemsFromInputLineOK() throws APIException {
        String lineContent = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        Assert.assertEquals(6, util.getListOfItemsFromInputLine(lineContent).size());
    }

}