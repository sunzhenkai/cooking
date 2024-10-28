async function test_bool() {
    return true;
}

function test_err() {
    throw new Error("GGG");
}

function test_trigger_err() {
    let r = test_err();
    return r;
}

function test_catch_err() {
    try {
        test_trigger_err();
    } catch (error) {
        console.log(`error is cachted ${error}`)
        // console.log(`error is cachted ${error}, stack: ${error.stack}`)
    }
}

test_catch_err();