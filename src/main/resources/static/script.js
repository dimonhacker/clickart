let shortLink =GetURLParameter("shortLink");
if(!!shortLink){
    document.getElementById("placeholder").innerText="Last link: "+shortLink;
}
function GetURLParameter(sParam)
{
    let sPageURL = window.location.search.substring(1);
    let sURLVariables = sPageURL.split('&');
    for (let i = 0; i < sURLVariables.length; i++)
    {
        let sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
}

function copy(link) {
  var copyText = document.getElementById(link);
    navigator.clipboard.writeText(copyText.href)
          .then(() => {
            alert("successfully copied");
          })
          .catch(() => {
            alert("something went wrong");
          });

}
