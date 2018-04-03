/**
 * string拓展js
 */
if(typeof String.prototype.endsWith !== 'function'){
    String.prototype.endsWith = function(str) {
        if (str === null || str === "" || str.length > this.length){
            return false;
        }
        return this.substring(this.length - str.length) === str;
    };
}
if(typeof String.prototype.startsWith !== 'function'){
    String.prototype.startsWith = function(str) {
        if (str === null || str === "" || str.length > this.length){
            return false;
        }
        return this.substring(0, str.length) === str;
    };
}