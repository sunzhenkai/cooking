async function init() {
    return new Promise((resolve, reject) => {
        reject("GGG");
    });
}

async function rej_error() {
    // let r = await init();
    let r = init();
    r.then((result) => {})
    .catch((error) => {
        console.error("error is cathed", error)
    })
}

async function catch_rej_error(params) {
    try {
        rej_error();
    } catch(error) {
        console.error('rej error is catched', error);
    }
}

catch_rej_error();