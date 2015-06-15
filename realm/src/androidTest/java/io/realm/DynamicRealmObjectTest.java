/*
 * Copyright 2015 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.realm;

import android.test.AndroidTestCase;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.realm.dynamic.DynamicRealmList;
import io.realm.dynamic.DynamicRealmObject;
import io.realm.entities.AllJavaTypes;

import static io.realm.internal.test.ExtraTests.assertArrayEquals;

public class DynamicRealmObjectTest extends AndroidTestCase {

    private Realm realm;
    private DynamicRealmObject dObj;

    @Override
    protected void setUp() throws Exception {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getContext()).schema(AllJavaTypes.class).build();
        Realm.deleteRealm(realmConfig);
        realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();
        AllJavaTypes obj = realm.createObject(AllJavaTypes.class);
        obj.setFieldString("str");
        obj.setFieldShort((short) 1);
        obj.setFieldInt(1);
        obj.setFieldLong(1);
        obj.setFieldFloat(1.23f);
        obj.setFieldDouble(1.234d);
        obj.setFieldBinary(new byte[]{1, 2, 3});
        obj.setFieldBoolean(true);
        obj.setFieldDate(new Date(1000));
        obj.setFieldObject(obj);
        obj.getFieldList().add(obj);
        dObj = new DynamicRealmObject(realm, obj.row);
        realm.commitTransaction();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        realm.close();
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetBooleanIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_STRING);
        for (String argument : arguments) {
            try {
                dObj.getBoolean(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetBoolean() {
        assertTrue(dObj.getBoolean(AllJavaTypes.FIELD_BOOLEAN));
    }

    public void testGetLinkedBoolean() {
        try {
            dObj.getBoolean(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_BOOLEAN);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetShortIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_STRING);
        for (String argument : arguments) {
            try {
                dObj.getShort(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetShort() {
        assertEquals(1, dObj.getShort(AllJavaTypes.FIELD_SHORT));
    }

    public void testGetLinkedShort() {
        try {
            dObj.getShort(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_SHORT);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetIntIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_STRING);
        for (String argument : arguments) {
            try {
                dObj.getInt(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetInt() {
        assertEquals(1, dObj.getInt(AllJavaTypes.FIELD_INT));
    }

    public void testGetLinkedInt() {
        try {
            dObj.getInt(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_INT);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetLongIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_STRING);
        for (String argument : arguments) {
            try {
                dObj.getLong(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetLong() {
        assertEquals(1, dObj.getLong(AllJavaTypes.FIELD_LONG));
    }

    public void testGetLinkedLong() {
        try {
            dObj.getLong(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_LONG);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetFloatIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_STRING);
        for (String argument : arguments) {
            try {
                dObj.getFloat(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetFloat() {
        assertEquals(1.23f, dObj.getFloat(AllJavaTypes.FIELD_FLOAT));
    }

    public void testGetLinkedFloat() {
        try {
            dObj.getFloat(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_FLOAT);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetDoubleIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_STRING);
        for (String argument : arguments) {
            try {
                dObj.getFloat(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetDouble() {
        assertEquals(1.234d, dObj.getDouble(AllJavaTypes.FIELD_DOUBLE));
    }

    public void testGetLinkedDouble() {
        try {
            dObj.getDouble(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_DOUBLE);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetBytesIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_STRING);
        for (String argument : arguments) {
            try {
                dObj.getBytes(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetBytes() {
        assertArrayEquals(new byte[]{1, 2, 3}, dObj.getBytes(AllJavaTypes.FIELD_BINARY));
    }

    public void testGetLinkedBytes() {
        try {
            dObj.getDouble(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_BINARY);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetDateIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_STRING);
        for (String argument : arguments) {
            try {
                dObj.getDate(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetDate() {
        assertEquals(new Date(1000), dObj.getDate(AllJavaTypes.FIELD_DATE));
    }

    public void testGetLinkedDate() {
        try {
            dObj.getDate(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_DATE);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetStringIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_INT);
        for (String argument : arguments) {
            try {
                dObj.getString(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetString() {
        assertEquals("str", dObj.getString(AllJavaTypes.FIELD_STRING));
    }

    public void testGetLinkedString() {
        try {
            dObj.getDate(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_STRING);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    // Test invalid input: <empty, non-existing field, wrong field type>
    public void testGetObjectIllegalArguments() {
        List<String> arguments = Arrays.asList(null, "foo", AllJavaTypes.FIELD_STRING);
        for (String argument : arguments) {
            try {
                dObj.getRealmObject(argument);
                fail(argument + " should throw exception.");
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    public void testGetObject() {
        assertEquals(dObj, dObj.getRealmObject(AllJavaTypes.FIELD_OBJECT));
    }

    public void testGetLinkedObject() {
        try {
            dObj.getRealmObject(AllJavaTypes.FIELD_OBJECT + "." + AllJavaTypes.FIELD_OBJECT);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testGetList() {
        DynamicRealmList list = dObj.getRealmList(AllJavaTypes.FIELD_LIST);
        assertEquals(1, list.size());
        assertEquals(dObj, list.get(0));
    }

    public void testIsNullWithNullNotSupportedField() {
        assertFalse(dObj.isNull(AllJavaTypes.FIELD_INT));
    }

    public void testIsNullTrue() {
        realm.beginTransaction();
        AllJavaTypes obj = realm.createObject(AllJavaTypes.class);
        realm.commitTransaction();

        assertTrue(new DynamicRealmObject(obj.realm, obj.row).isNull(AllJavaTypes.FIELD_OBJECT));
    }

    public void testIsNullFalse() {
        assertFalse(dObj.isNull(AllJavaTypes.FIELD_OBJECT));
    }

    public void testGetKeys() {
        String[] expectedKeys = { AllJavaTypes.FIELD_STRING, AllJavaTypes.FIELD_SHORT, AllJavaTypes.FIELD_INT,
                AllJavaTypes.FIELD_LONG, AllJavaTypes.FIELD_FLOAT, AllJavaTypes.FIELD_DOUBLE, AllJavaTypes.FIELD_BOOLEAN,
                AllJavaTypes.FIELD_DATE, AllJavaTypes.FIELD_BINARY, AllJavaTypes.FIELD_OBJECT, AllJavaTypes.FIELD_LIST };
        String[] keys = dObj.getFieldNames();
        assertArrayEquals(expectedKeys, keys);
    }

//    public void testEquals() {
//        AllJavaTypes obj1 = realm.where(AllJavaTypes.class).findFirst();
//        AllJavaTypes obj2 = realm.where(AllJavaTypes.class).findFirst();
//        DynamicRealmObject dObj1 = new DynamicRealmObject(realm, obj1.row);
//        DynamicRealmObject dObj2 = new DynamicRealmObject(realm, obj2.row);
//        assertTrue(dObj1.equals(dObj2));
//    }

    public void testHashcode() {
        AllJavaTypes obj1 = realm.where(AllJavaTypes.class).findFirst();
        DynamicRealmObject dObj1 = new DynamicRealmObject(realm, obj1.row);
        assertEquals(obj1.hashCode(), dObj1.hashCode());
    }

//    public void testToString() {
//        // TODO
//    }
}
