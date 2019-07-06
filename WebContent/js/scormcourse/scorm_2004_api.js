window.API_1484_11 = new API();
var track_scrom_start_time;
var track_scorm_end_time;
function API() {
    var _self = this;
    var SCORM_TRUE = 'true';
    var SCORM_FALSE = 'false';

    var STATE_NOT_INITIALIZED = 0;
    var STATE_INITIALIZED = 1;
    var STATE_COMPLETE = 2;

    var LOG_LEVEL_DEBUG = 1;
    var LOG_LEVEL_INFO = 2;
    var LOG_LEVEL_WARNING = 3;
    var LOG_LEVEL_ERROR = 4;

    _self.cmi = new CMI(_self);
    _self.adl = new ADL(_self);
    _self.lastErrorCode = 0;
    _self.currentState = STATE_NOT_INITIALIZED;

    _self.Initialize = Initialize;
    _self.Terminate = Terminate;
    _self.GetValue = GetValue;
    _self.SetValue = SetValue;
    _self.Commit = Commit;
    _self.GetLastError = GetLastError;
    _self.GetErrorString = GetErrorString;
    _self.GetDiagnostic = GetDiagnostic;
    _self.setValueUsingLMSBeforeInit = setCMIValue;
    _self.getValueUsingLMSBeforeInit = getCMIValue;
    //Diagnostic functions

    _self.apiLogLevel = LOG_LEVEL_DEBUG;
    _self.apiLog = apiLog;
    _self.on = onListener;
    _self.listenerArray = [];
    _self.throwSCORMError = throwSCORMError;

    /**
     * @returns {string} bool
     */
    function Initialize() {
        var return_value = SCORM_FALSE;

        if (_self.currentState === STATE_INITIALIZED) {
            throwSCORMError(_self, 101, 'LMS was already initialized!');
        }
        else {
        	track_scrom_start_time = new Date();
            _self.currentState = STATE_INITIALIZED;
            return_value = SCORM_TRUE;
        }

        processListeners('Initialize');

        _self.apiLog('Initialize', null, 'returned: ' + return_value, LOG_LEVEL_INFO);

        return return_value;
    }

    /**
     * @returns {string} bool
     */
    function Terminate() {
        checkState();
         track_scrom_end_time = new Date();
        _self.apiLog('Terminate', '', null, LOG_LEVEL_INFO);
         var _nav_request = window.API_1484_11.GetValue("adl.nav.request"); 
         if(_nav_request == "suspendAll" || _nav_request == "exitAll"){ 
        	 closeScormCourse();
          }
        processListeners('Terminate');
        return SCORM_TRUE;
    }

    /**
     * @param CMIElement
     * @returns {string}
     */
    function GetValue(CMIElement) {
        checkState();

        var elementValue = getCMIValue(CMIElement);

        _self.apiLog('GetValue', CMIElement, ': returned: ' + elementValue, LOG_LEVEL_INFO);

        processListeners('GetValue', CMIElement);

        return elementValue;
    }

    /**
     * @param CMIElement
     * @param value
     * @returns {string}
     */
    function SetValue(CMIElement, value) {
    	
        checkState();

        var setResult = setCMIValue(CMIElement, value);
        
        _self.apiLog('SetValue', CMIElement, ': ' + value + ': result: ' + setResult, LOG_LEVEL_INFO);

        processListeners('SetValue', CMIElement, value);

        return setResult;
    }

    /**
     * Orders LMS to store all content parameters
     */
    function Commit() {
        checkState();

        processListeners('Commit');

        _self.apiLog('Commit', '', null, LOG_LEVEL_INFO);
    }

    /**
     * Returns last error code
     *
     * @returns {string|null}
     */
    function GetLastError() {
        checkState();

        if (_self.lastErrorCode !== null) {
            _self.apiLog('GetLastError', null, 'returned: ' + _self.lastErrorCode, LOG_LEVEL_INFO);
        }

        processListeners('GetLastError');

        return _self.lastErrorCode;
    }

    /**
     * Returns the errorNumber error description
     *
     * @param CMIErrorCode
     * @returns {string}
     */
    function GetErrorString(CMIErrorCode) {
        checkState();

        if (CMIErrorCode !== null && CMIErrorCode !== '') {
            var errorString = getLmsErrorMessageDetails(CMIErrorCode);

            _self.apiLog('GetErrorString', null, 'returned: ' + errorString, LOG_LEVEL_INFO);

            return errorString;
        }

        processListeners('GetErrorString');

        return '';
    }

    /**
     * Returns an comprehensive description of the errorNumber error.
     *
     * @param CMIErrorCode
     * @returns {string}
     */
    function GetDiagnostic(CMIErrorCode) {
        checkState();

        if (CMIErrorCode !== null && CMIErrorCode !== '') {
            var errorString = getLmsErrorMessageDetails(CMIErrorCode, true);

            _self.apiLog('GetDiagnostic', null, 'returned: ' + _self.lastErrorCode, LOG_LEVEL_INFO);

            return errorString;
        }

        processListeners('GetDiagnostic');

        return '';
    }

    /**
     * Logging for all SCORM actions
     *
     * @param functionName
     * @param CMIElement
     * @param logMessage
     * @param messageLevel
     */
    function apiLog(functionName, CMIElement, logMessage, messageLevel) {
        logMessage = formatMessage(functionName, CMIElement, logMessage);

        if (messageLevel >= _self.apiLogLevel) {
            switch (messageLevel) {
                case LOG_LEVEL_ERROR:
                    console.error(logMessage);
                    break;
                case LOG_LEVEL_WARNING:
                    console.warn(logMessage);
                    break;
                case LOG_LEVEL_INFO:
                    console.info(logMessage);
                    break;
            }
        }
    }

    /**
     * Formats the scorm messages for easy reading
     *
     * @param functionName
     * @param CMIElement
     * @param message
     * @returns {string}
     */
    function formatMessage(functionName, CMIElement, message) {
        var baseLength = 20;
        var messageString = '';

        messageString += functionName;

        var fillChars = baseLength - messageString.length;

        for (var i = 0; i < fillChars; i++) {
            messageString += ' ';
        }

        messageString += ': ';

        if (CMIElement) {
            var CMIElementBaseLength = 70;

            messageString += CMIElement;

            fillChars = CMIElementBaseLength - messageString.length;

            for (var j = 0; j < fillChars; j++) {
                messageString += ' ';
            }
        }

        if (message) {
            messageString += message;
        }

        return messageString;
    }

    /**
     * Provides a mechanism for attaching to a specific scorm event
     *
     * @param listenerString
     * @param callback
     */
    function onListener(listenerString, callback) {
        if (!callback) {
            return;
        }

        var listenerSplit = listenerString.split('.');

        if (listenerSplit.length === 0) {
            return;
        }

        var functionName = listenerSplit[0];
        var CMIElement = null;

        if (listenerSplit.length > 1) {
            CMIElement = listenerString.replace(functionName + '.', '');
        }

        _self.listenerArray.push(
            {
                functionName: functionName,
                CMIElement: CMIElement,
                callback: callback
            }
        )
    }

    /**
     * Processes any "on" listeners that have been created
     *
     * @param functionName
     * @param CMIElement
     * @param value
     */
    function processListeners(functionName, CMIElement, value) {
        for (var i = 0; i < _self.listenerArray.length; i++) {
            var listener = _self.listenerArray[i];

            if (listener.functionName === functionName) {
                if (listener.CMIElement && listener.CMIElement === CMIElement) {
                    listener.callback(CMIElement, value);
                }
                else {
                    listener.callback(CMIElement, value);
                }
            }
        }
    }

    /**
     * Throws a scorm error
     *
     * @param API
     * @param errorNumber
     * @param message
     */
    function throwSCORMError(API, errorNumber, message) {
        if (!message) {
            message = getLmsErrorMessageDetails(errorNumber);
        }

        _self.apiLog('throwSCORMError', null, errorNumber + ': ' + message, LOG_LEVEL_ERROR);

        API.lastErrorCode = String(errorNumber);
    }

    /**
     * Checks the LMS state and ensures it has been initialized
     */
    function checkState() {
        if (_self.currentState !== STATE_INITIALIZED) {
            throwSCORMError(_self, 301);
        }
    }

    /**
     * Sets a value on the CMI Object
     *
     * @param CMIElement
     * @param value
     * @returns {string}
     */
    function setCMIValue(CMIElement, value) {
        if (!CMIElement || CMIElement === '') {
            return SCORM_FALSE;
        }

        var structure = CMIElement.split('.');
        var refObject = _self;
        var found = SCORM_FALSE;
        if(structure[0].indexOf('adl')>-1){
            for (var i = 0; i < structure.length; i++) {
                if (i === structure.length - 1) {
                    if (!refObject.hasOwnProperty(structure[i])) {
                        throwSCORMError(_self, 101, 'setCMIValue did not find an element for: ' + CMIElement);
                    }
                    else {
                        refObject[structure[i]] = value;
                        found = SCORM_TRUE;
                    }
                }
                else {
                    refObject = refObject[structure[i]];
                    if(structure[i]==null || structure[i].length==0){
                    	i++;
                    }
                }
            }
            
        }
        else
        	{
        for (var i = 0; i < structure.length; i++) {
            if (i === structure.length - 1) {
                if (!refObject.hasOwnProperty(structure[i])) {
                    throwSCORMError(_self, 101, 'setCMIValue did not find an element for: ' + CMIElement);
                }
                else {
                    refObject[structure[i]] = value;
                    found = SCORM_TRUE;
                }
            }
            else {
                refObject = refObject[structure[i]];
                if(structure[i]==null || structure[i].length==0){
                	i++;
                }
                else if (refObject.hasOwnProperty('childArray')) {
                    var index = parseInt(structure[i + 1]);

                    //SCO is trying to set an item on an array
                    if (!isNaN(index)) {
                        var item = refObject.childArray[index];
                        if (item) {
                            refObject = item;
                        }
                        else {
                            var newChild;
                            
                            if (CMIElement.indexOf('cmi.objectives') > -1) {
                                newChild = new ObjectivesObject(_self);
                            }
                            
                            else if(CMIElement.indexOf('cmi.comments_from_learner') > -1){
                            	newChild = new comments_from_learnerObject(_self);
                            }
                            else if (CMIElement.indexOf('cmi.comments_from_lms') > -1) {
                                newChild = new comments_from_lmsObject(_self);
                            }
                            else if (CMIElement.indexOf('.correct_responses') > -1) {
                                newChild = new InteractionsCorrectResponsesObject(_self);
                            }
                            else if (CMIElement.indexOf('.objectives') > -1) {
                                newChild = new InteractionsObjectivesObject(_self);
                            }
                            else if (CMIElement.indexOf('cmi.interactions') > -1) {
                                newChild = new InteractionsObject(_self);
                            }
                            else if (CMIElement.indexOf('cmi.learner_preference') > -1) {
                                newChild = new learner_preferenceObject(_self);
                            }
                            if (!newChild) {
                                throwSCORMError(_self, 101, 'Cannot create new sub entity: ' + CMIElement);
                            }
                            else {
                                refObject.childArray.push(newChild);

                                refObject = newChild;
                            }
                        }

                        //Have to update i value to skip the array position
                        i++;
                    }
                }
             }
           }
         }
        if (found === SCORM_FALSE) {
            _self.apiLog('There was an error setting the value for: ' + CMIElement + ', value of: ' + value, LOG_LEVEL_WARNING);
        }

        return found;
    }

    /**
     * Gets a value from the CMI Object
     *
     * @param CMIElement
     * @returns {*}
     */
    function getCMIValue1(CMIElement) {
        if (!CMIElement || CMIElement === '') {
            return '';
        }
        
        var structure = CMIElement.split('.');
        var refObject = _self;
        var lastProperty = null;
        for (var i = 0; i < structure.length; i++) {
            lastProperty = structure[i];
            if (i === structure.length - 1) {
                if (!refObject.hasOwnProperty(structure[i])) {
                    throwSCORMError(_self, 101, 'getCMIValue did not find a value for: ' + CMIElement);
                }
            }

            refObject = refObject[structure[i]];
        }

        if (refObject === null || refObject === undefined) {
            if (lastProperty === '_children') {
                throwSCORMError(_self, 202);
            }
            else if (lastProperty === '_count') {
                throwSCORMError(_self, 203);
            }
            return '';
        }
        else {
            return refObject;
        }
    }
    
    /**
     * Gets a value from the CMI Object
     *
     * @param CMIElement
     * @returns {*}
     */
    function getCMIValue(CMIElement) {
        if (!CMIElement || CMIElement === '') {
            return '';
        }
        
        var structure = CMIElement.split('.');
        var refObject = _self;
        var lastProperty = null;
        for (var i = 0; i < structure.length; i++) {
            if (i === structure.length - 1) {
                if (!refObject.hasOwnProperty(structure[i])) {
                	throwSCORMError(_self, 101, 'getCMIValue did not find a value for: ' + CMIElement);
                }
                else {
                	refObject = refObject[structure[i]];
                     }
            }
            else {
                refObject = refObject[structure[i]];
                if(structure[i]==null || structure[i].length==0){
                	i++;
                }
                else if (refObject.hasOwnProperty('childArray')) {
                    var index = parseInt(structure[i + 1]);
                    if (!isNaN(index)) {
                        var item = refObject.childArray[index];
                        if (item) {
                            refObject = item;
                        }
                        i++;
                    }
                }
            }
        }
       
        if (refObject === null || refObject === undefined) {
            if (lastProperty === '_children') {
                throwSCORMError(_self, 202);
            }
            else if (lastProperty === '_count') {
                throwSCORMError(_self, 203);
            }
            return '';
        }
        else {
            return refObject;
        }
    }

    /**
     * Returns the message that corresponds to errrorNumber.
     */
    function getLmsErrorMessageDetails(errorNumber, detail) {
        var basicMessage = '';
        var detailMessage = '';

        //Set error number to string since inconsistent from modules if string or number
        errorNumber = String(errorNumber);

        switch (errorNumber) {
	        case '0':
	            basicMessage = 'No Error';
	            detailMessage = 'No error occurred, the previous API call was successful';
	            break;
            
            case '101':
                basicMessage = 'General Exception';
                detailMessage = 'No specific error code exists to describe the error. Use GetDiagnostic for more information';
                break;
            
            case '102':
                basicMessage = 'General Initialization Failure';
                detailMessage = 'Call to Initialize failed for an unknown reason';
                break;
              
            case '103':
                basicMessage = 'Already Initialized';
                detailMessage = 'Call to Initialize failed because Initialize was already called';
                break;
             
            case '104':
                basicMessage = 'Content Instance Terminated';
                detailMessage = 'Call to Initialize failed because Terminate was already called.';
                break;
                
            case '111':
                basicMessage = 'General Termination Failure';
                detailMessage = 'Call to Terminate failed for an unknown reason';
                break;
                
            case '112':
                basicMessage = 'Termination Before Initialization';
                detailMessage = 'Call to Terminate failed because it was made before the call to Initialize';
                break;
                
            case '113':
                basicMessage = 'Termination After Termination';
                detailMessage = 'Call to Terminate failed because Terminate was already called';
                break;
                
            case '122':
                basicMessage = 'Retrieve Data Before Initialization';
                detailMessage = 'Call to GetValue failed because it was made before the call to Initialize';
                break;
                
            case '123':
                basicMessage = 'Retrieve Data After Termination';
                detailMessage = 'Call to GetValue failed because it was made after the call to Terminate.';
                break;
                
            case '132':
                basicMessage = 'Store Data Before Initialization';
                detailMessage = 'Call to SetValue failed because it was made before the call to Initialize';
                break;
                
            case '133':
                basicMessage = 'Store Data After Termination';
                detailMessage = 'Call to SetValue failed because it was made after the call to Terminate';
                break;
                
            case '142':
                basicMessage = 'Commit Before Initialization';
                detailMessage = 'Call to Commit failed because it was made before the call to Initialize';
                break;
                
            case '143':
                basicMessage = 'Commit After Termination';
                detailMessage = 'Call to Commit failed because it was made after the call to Terminate';
                break;
    
            case '201':
                basicMessage = 'Invalid argument error';
                detailMessage = 'An invalid argument was passed to an API method (usually indicates that Initialize, Commit or Terminate did not receive the expected empty string argument';
                break;

            case '301':
                basicMessage = 'General Get Failure';
                detailMessage = 'Indicates a failed GetValue call where no other specific error code is applicable. Use GetDiagnostic for more information.';
                break;
                
            case '351':
                basicMessage = 'General Set Failure';
                detailMessage = 'Indicates a failed SetValue call where no other specific error code is applicable. Use GetDiagnostic for more information.';
                break;
                
            case '391':
                basicMessage = 'General Commit Failure';
                detailMessage = 'Indicates a failed Commit call where no other specific error code is applicable. Use GetDiagnostic for more information.';
                break;

            case '401':
                basicMessage = 'Undefined Data Model Element';
                detailMessage =
                    'The data model element name passed to GetValue or SetValue is not a valid SCORM data model element.';
                break;

            case '402':
                basicMessage = 'Unimplemented Data Model Element';
                detailMessage =
                    'The data model element indicated in a call to GetValue or SetValue is valid, but was not implemented by this LMS. In SCORM 2004, this error would indicate an LMS that is not fully SCORM conformant.';
                break;

            case '403':
                basicMessage = 'Data Model Element Value Not Initialized';
                detailMessage = 'Attempt to read a data model element that has not been initialized by the LMS or through a SetValue call. This error condition is often reached during normal execution of a SCO.';
                break;

            case '404':
                basicMessage = 'Data Model Element Is Read Only';
                detailMessage = 'SetValue was called with a data model element that can only be read.';
                break;

            case '405':
                basicMessage = 'Data Model Element Is Write Only';
                detailMessage = 'GetValue was called on a data model element that can only be written to.';
                break;
                
            case '406':
                basicMessage = 'Data Model Element Type Mismatch';
                detailMessage = 'SetValue was called with a value that is not consistent with the data format of the supplied data model element.';
                break;
                
            case '407':
                basicMessage = 'Data Model Element Value Out Of Range';
                detailMessage = 'The numeric value supplied to a SetValue call is outside of the numeric range allowed for the supplied data model element.';
                break;
                
            case '408':
                basicMessage = 'Data Model Dependency Not Established';
                detailMessage = 'Some data model elements cannot be set until another data model element was set. This error condition indicates that the prerequisite element was not set before the dependent element.';
                break;

            default:
                basicMessage = 'No Error';
                detailMessage = 'No Error';
                break;
        }

        if (detail) {
            return detailMessage;
        }
        else {
            return basicMessage;
        }
    }

    return _self;
}

/**
 * ADL data model
 *
 * based on the Scorm CMI 1.2 definition at http://scorm.com/scorm-explained/technical-scorm/run-time/run-time-reference/
 */
function ADL(API) {
    return {
         jsonString: false,

         nav : {
         	_request : 'continue, previous, choice, jump, exit, exitAll, abandon, abandonAll, suspendAll _none_',
         	get request(){
         		return this._request;
         	},
             set request(request){
             	this._request = request;
             },
             
             request_valid:{
             	
             	_continue: 'unknown',
             	
             	get 'continue'(){
             		return this._continue;
             	},
             	set 'continue'(continue_data){
             		this._continue = continue_data;
             	},
             	
                 _previous: 'unknown',
             	
             	get previous(){
             		return this._previous;
             	},
             	set previous(previous){
             		this._previous = previous;
             	}
             	
             }
         },
        
        toJSON: jsonFormatter
    };
}
/**
 * Cmi data model
 *
 * based on the Scorm CMI 1.2 definition at http://scorm.com/scorm-explained/technical-scorm/run-time/run-time-reference/
 */
function CMI(API) {
	
    return {
         jsonString: false,
        
        _completion_status: 'unknown',
        
        get completion_status() {
            return this._completion_status;
        },
        set completion_status(completion_status) {
            this._completion_status = completion_status;
        },
        
        _location: '',
        
        get location() {
            return this._location;
        },
        set location(location) {
            this._location = location;
        },
        
         _exit: 'suspend',
        
        get exit() {
        	 API.throwSCORMError(API, 405); //write only
        },
        
        set exit(exit) {
            this._exit = exit;
        },
        
        _progress_measure: '',
        
        get progress_measure() {
            return this._progress_measure;
        },
        set progress_measure(progress_measure) {
            this._progress_measure = progress_measure;
        },
        
        _success_status: 'unknown',
        
        get success_status() {
            return this._success_status;
        },
        set success_status(success_status) {
            this._success_status = success_status;
        },
        
        _suspend_data: '',
        
        get suspend_data() {
            return this._suspend_data;
        },
        set suspend_data(suspend_data) {
            this._suspend_data = suspend_data;
        },
        
        _completion_threshold: '',
        
        get completion_threshold() {
            return this._completion_threshold;
        },
        set completion_threshold(completion_threshold) {
        	API.throwSCORMError(API, 404); // read only
        },
        
        _credit: 'credit',
        
        get credit() {
            return this._credit;
        },
        set credit(credit) {
        	API.throwSCORMError(API, 404); // read only
        },
        
        _entry: '',
        
        get entry() {
            return this._entry;
        },
        set entry(entry) {
        	API.throwSCORMError(API, 404); // read only
        },
        
        _launch_data: '',
        
        get launch_data() {
            return this._launch_data;
        },
        set launch_data(launch_data) {
        	API.throwSCORMError(API, 404); // read only
        },
        
        _learner_id: '',
        
        get learner_id() {
            return this._learner_id;
        },
        set learner_id(learner_id) {
        	this._learner_id = learner_id; // read only
        },
        
        _learner_name: '',
        
        get learner_name() {
            return this._learner_name;
        },
        set learner_name(learner_name) {
        	this._learner_name = learner_name; // read only
        },
        
        _max_time_allowed: '',
        
        get max_time_allowed() {
            return this._max_time_allowed;
        },
        set max_time_allowed(max_time_allowed) {
        	API.throwSCORMError(API, 404); // read only
        },
        
        _mode: 'normal',
        
        get mode() {
            return this._mode;
        },
        set mode(mode) {
        	API.throwSCORMError(API, 404); // read only
        },
        
        _scaled_passing_score: '',
        
        get scaled_passing_score() {
            return this._scaled_passing_score;
        },
        set scaled_passing_score(scaled_passing_score) {
        	API.throwSCORMError(API, 404); // read only
        },
        
        _session_time: '0000:00:00',
        
        get session_time() {
        	return this._session_time;
        	//API.throwSCORMError(API, 405); // write only
        },
        
        set session_time(session_time) {
            this._session_time = session_time;
        },
        
        _time_limit_action: 'exit,you have exceed time limit',
        
        get time_limit_action() {
            return this._time_limit_action;
        },
        
        set time_limit_action(time_limit_action) {
        	API.throwSCORMError(API, 404); // read only
        },
        
        _total_time: '0000:00:00',
        
        get total_time() {
            return this._total_time;
        },
        
        set total_time(total_time) {
        	API.throwSCORMError(API, 404); // read only
        },
        
        comments_from_learner: {
            __children: 'comment,location,timestamp',
            childArray: [],

            get _children() {
                return this.__children;
            },
            set _children(_children) {
                API.throwSCORMError(API, 404);
            },
            get _count() {
                return this.childArray.length.toString();
            },
            set _count(count) {
                API.throwSCORMError(API, 404);
            },
            toJSON: jsonFormatter
        },
        
        comments_from_lms: {
            __children: 'comment,location,timestamp',
            childArray: [],

            get _children() {
                return this.__children;
            },
            set _children(_children) {
                API.throwSCORMError(API, 404);
            },
            get _count() {
                return this.childArray.length.toString();
            },
            set _count(count) {
                API.throwSCORMError(API, 404);
            },
            toJSON: jsonFormatter
        },
        
        interactions: {
            __children: 'id,type,objectives,timestamp,correct_responses,weighting,learner_response,result,latency,description',
            childArray: [],

            get _children() {
                return this.__children;
            },
            set _children(_children) {
                API.throwSCORMError(API, 404);
            },
            get _count() {
                return this.childArray.length.toString();
            },
            set _count(_count) {
                API.throwSCORMError(API, 404);
            },
            toJSON: jsonFormatter
        },
        
        learner_preference: {
            __children: 'audio_level,language,delivery_speed,audio_captioning',
            childArray: [],

            get _children() {
                return this.__children;
            },
            set _children(_children) {
                API.throwSCORMError(API, 404);
            },
            get _count() {
                return this.childArray.length.toString();
            },
            set _count(_count) {
                API.throwSCORMError(API, 404);
            },
            toJSON: jsonFormatter
        },
        
        objectives: {
            __children: 'id,score,success_status,completion_status,description,progress_measure',
            childArray: [],

            get _children() {
                return this.__children;
            },
            set _children(_children) {
                API.throwSCORMError(API, 404);
            },
            get _count() {
                return this.childArray.length.toString();
            },
            set _count(count) {
                API.throwSCORMError(API, 404);
            },
            toJSON: jsonFormatter
        },
        
        score: {
            __children: 'scaled,raw,min,max',
            get _children() {
                return this.__children;
            },
            set _children(children) {
                API.throwSCORMError(API, 404);
            },
            
            _scaled: '',
            _raw: '',
            _max: '',
            _min: '',
            
            get scaled() {
                return this._scaled;
            },
            set scaled(scaled) {
                this._scaled = scaled;
            },
            get raw() {
                return this._raw;
            },
            set raw(raw) {
                this._raw = raw;
            },
            get max() {
                return this._max;
            },
            set max(max) {
                this._max = max;
            },
            get min() {
                return this._min;
            },
            set min(min) {
                this._min = min;
            },
            toJSON: jsonFormatter
        },
        
        toJSON: jsonFormatter
    };
}



function comments_from_learnerObject(API) {
    return {
        jsonString: false,
        _comment: '',
        _location: '',
        _timestamp: '',

        get comment() {
                return this._comment;
        },
        set comment(comment) {
            this._comment = comment;
        },
        
        get location() {
                return this._location;
        },
        set location(location) {
            this._location = location;
        },
        get timestamp() {
                return this._timestamp;
        },
        set timestamp(timestamp) {
            this._timestamp = timestamp;
        },
        toJSON: jsonFormatter
    }
}

function comments_from_lmsObject(API) {
    return {
        jsonString: false,
        _comment: '',
        _location: '',
        _timestamp: '',

        get comment() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._comment;
            }
        },
        set comment(comment) {
            this._comment = comment;
        },
        get location() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._location;
            }
        },
        set location(location) {
            this._location = location;
        },
        get timestamp() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._timestamp;
            }
        },
        set timestamp(timestamp) {
            this._timestamp = timestamp;
        },
        toJSON: jsonFormatter
    }
}

function InteractionsObject(API) {
    return {
        jsonString: false,
        _id: '',
        _timestamp: '',
        _type: '',
        _weighting: '',
        _learner_response: '',
        _result: '',
        _latency: '',
        _description: '',
        get id() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._id;
            }
        },
        set id(id) {
            this._id = id;
        },
        get timestamp() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._timestamp;
            }
        },
        set timestamp(timestamp) {
            this._timestamp = timestamp;
        },
        get type() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._type;
            }
        },
        set type(type) {
            this._type = type;
        },
        get weighting() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._weighting;
            }
        },
        set weighting(weighting) {
            this._weighting = weighting;
        },
        get learner_response() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._learner_response;
            }
        },
        set learner_response(learner_response) {
            this._learner_response = learner_response;
        },
        get result() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._result;
            }
        },
        set result(result) {
            this._result = result;
        },
        get latency() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._latency;
            }
        },
        set latency(latency) {
            this._latency = latency;
        },
        objectives: {
            childArray: [],

            get _count() {
                return this.childArray.length.toString();
            },
            set _count(_count) {
                API.throwSCORMError(API, 402);
            },
            toJSON: jsonFormatter
        },
        correct_responses: {
            childArray: [],

            get _count() {
                return this.childArray.length;
            },
            set _count(_count) {
                API.throwSCORMError(API, 402);
            },
            toJSON: jsonFormatter
        },
        
        get description() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._description;
            }
        },
        set description(description) {
            this._description = description;
        },
        
        toJSON: jsonFormatter
    }
}

function InteractionsObjectivesObject(API) {
    return {
        jsonString: false,
        _id: '',

        get id() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._id;
            }
        },
        set id(id) {
            this._id = id;
        },
        toJSON: jsonFormatter
    };
}

function InteractionsCorrectResponsesObject(API) {
    return {
        jsonString: false,
        _pattern: '',

        get pattern() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._pattern;
            }
        },
        set pattern(pattern) {
            this._pattern = pattern;
        },
        toJSON: jsonFormatter
    };
}

function learner_preferenceObject(API){
	return {
        jsonString: false,
        _audio_level: '',
        _language: '',
        _delivery_speed: '',
        _audio_captioning: '',

        get audio_level() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._audio_level;
            }
        },
        set audio_level(audio_level) {
            this._audio_level = audio_level;
        },
        
        get language() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._language;
            }
        },
        set language(language) {
            this._language = language;
        },
        
        get delivery_speed() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._delivery_speed;
            }
        },
        set delivery_speed(delivery_speed) {
            this._delivery_speed = delivery_speed;
        },
        
        get audio_captioning() {
            if (!this.jsonString) {
                API.throwSCORMError(API, 404);
            }
            else {
                return this._audio_captioning;
            }
        },
        set audio_captioning(audio_captioning) {
            this._audio_captioning = audio_captioning;
        },
        toJSON: jsonFormatter
    };
}

function ObjectivesObject(API) {
    return {
        _id: '',
        get id() {
            return this._id;
        },
        set id(id) {
            this._id = id;
        },
        score: {
            __children: 'scaled,raw,min,max',
            get _children() {
                return this.__children;
            },
            set _children(children) {
                API.throwSCORMError(API, 402);
            },
            
            _scaled: '',
            _raw: '',
            _max: '',
            _min: '',
            
            get scaled() {
                return this._scaled;
            },
            set scaled(scaled) {
                this._scaled = scaled;
            },
            get raw() {
                return this._raw;
            },
            set raw(raw) {
                this._raw = raw;
            },
            get max() {
                return this._max;
            },
            set max(max) {
                this._max = max;
            },
            get min() {
                return this._min;
            },
            set min(min) {
                this._min = min;
            },
            toJSON: jsonFormatter
        },
        
        _success_status : '',
        
        get success_status() {
            return this._success_status;
        },
        set success_status(success_status) {
            this._success_status = success_status;
        },
        
        _completion_status : '',
        
        get completion_status() {
            return this._completion_status;
        },
        set completion_status(completion_status) {
            this._completion_status = completion_status;
        },
        
        _progress_measure : '',
        
        get progress_measure() {
            return this._progress_measure;
        },
        set progress_measure(progress_measure) {
            this._progress_measure = progress_measure;
        },
        
        _description : '',
        
        get description() {
            return this._description;
        },
        set description(description) {
            this._description = description;
        },
        
        toJSON: jsonFormatter
    }
}

function jsonFormatter() {
    this.jsonString = true;
    delete this.toJSON;

    var jsonValue = JSON.stringify(this);

    this.toJSON = jsonFormatter;
    this.jsonString = false;

    var returnObject = JSON.parse(jsonValue);

    for (var key in returnObject) {
        if (returnObject.hasOwnProperty(key)) {
            if (key.indexOf('_') === 0) {
                delete returnObject[key];
            }
        }
    }

    delete returnObject.jsonString;

    return returnObject;
}
