/*******************************************************************************
 * Copyright (c) 2019, 2019 IBM Corp. and others
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which accompanies this
 * distribution and is available at https://www.eclipse.org/legal/epl-2.0/
 * or the Apache License, Version 2.0 which accompanies this distribution and
 * is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * This Source Code may also be made available under the following
 * Secondary Licenses when the conditions for such availability set
 * forth in the Eclipse Public License, v. 2.0 are satisfied: GNU
 * General Public License, version 2 with the GNU Classpath
 * Exception [1] and GNU General Public License, version 2 with the
 * OpenJDK Assembly Exception [2].
 *
 * [1] https://www.gnu.org/software/classpath/license.html
 * [2] http://openjdk.java.net/legal/assembly-exception.html
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0 OR GPL-2.0 WITH Classpath-exception-2.0 OR LicenseRef-GPL-2.0 WITH Assembly-exception
 *******************************************************************************/

package jit.test.recognizedMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TestJavaLangStrictMath {

    /*
    * The following test is for testing the java_lang_StrictMath_sqrt function. 
    * In this test, we are expecting the calling to the function to be transformed into a dsqrt node in recognizedCallTransformer.
    * Also, if the input of the function is a double type constant, we should see the dsqrt node being reduced to dconst in treeSimplification.
    * The above transformation and reduction will be performed when the function get executed twice, therefore, we set the invocationCount to 2.
    * The test include the corner cases that sqrt requires in document.
    */
    @Test(groups = {"level.sanity"}, invocationCount=2)
    public void test_java_lang_StrictMath_sqrt() {
        AssertJUnit.assertTrue(Double.isNaN(StrictMath.sqrt(Double.NEGATIVE_INFINITY)));
        AssertJUnit.assertTrue(Double.isNaN(StrictMath.sqrt((double)-42.25)));
        AssertJUnit.assertEquals(-0.0, StrictMath.sqrt((double)-0.0));
        AssertJUnit.assertEquals(+0.0, StrictMath.sqrt((double)+0.0));
        AssertJUnit.assertEquals(7.5, StrictMath.sqrt((double)56.25));
        AssertJUnit.assertEquals(Double.POSITIVE_INFINITY, StrictMath.sqrt(Double.POSITIVE_INFINITY));
        AssertJUnit.assertTrue(Double.isNaN(StrictMath.sqrt(Double.NaN)));
    }
}
