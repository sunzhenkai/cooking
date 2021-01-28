import React from 'react'; 
import Upload from '../pages/upload';
import { BrowserRouter as Router, Route} from "react-router-dom";

function router(){
return (
<Router>
    <Route path="/upload" component={Upload} />
</Router>);
}

export default router;