/**
 * header components
 */

 export function HeadLine(props){
    console.log(props);

    return (
	
    <div>
    <p>
        Get started by editing&nbsp;
        {props.children}
    </p>

    <h1 id="props_title">{props.page} Page</h1>
    </div>
    )
}