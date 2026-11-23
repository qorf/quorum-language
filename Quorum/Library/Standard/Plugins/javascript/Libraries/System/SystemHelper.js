// The JavaScript heap's used, total and maximum bytes, or null if unknown.
function global_System_Memory_() {
    if (typeof process !== "undefined" && process.memoryUsage) {
        var usage = process.memoryUsage();
        return { used: usage.heapUsed, total: usage.heapTotal, maximum: Math.max(usage.rss, usage.heapTotal) };
    }
    if (typeof performance !== "undefined" && performance.memory) {
        return { used: performance.memory.usedJSHeapSize, total: performance.memory.totalJSHeapSize, maximum: performance.memory.jsHeapSizeLimit };
    }
    return null;
}

function plugins_quorum_Libraries_System_SystemHelper_() {
    // Waits on Node.js, where Atomics.wait may block. A browser's page
    // can't block, so there Sleep still returns at once.
    this.Sleep$quorum_integer = function (milliseconds) {
        if (milliseconds <= 0 || typeof process === "undefined" || typeof SharedArrayBuffer === "undefined")
            return;
        try {
            Atomics.wait(new Int32Array(new SharedArrayBuffer(4)), 0, 0, milliseconds);
        } catch (e) {
        }
    }
    
    this.RequestGarbageCollection = function () {
    }
    
    // Memory in bytes, where the platform reports it: Node.js's
    // process.memoryUsage, or the browser's performance.memory (Chromium
    // only). Elsewhere a browser can't know, and these stay 0.
    this.GetFreeMemory = function () {
        var memory = global_System_Memory_();
        return memory === null ? 0.0 : memory.total - memory.used;
    }
    
    this.GetMaximumMemory = function () {
        var memory = global_System_Memory_();
        return memory === null ? 0.0 : memory.maximum;
    }
    
    this.GetTotalMemory = function () {
        var memory = global_System_Memory_();
        return memory === null ? 0.0 : memory.total;
    }
    
    this.GetAvailableProcessors = function () {
        return 1;
    }
    
    this.GetThreadName = function () {
        return "1";
    }

    this.Exit$quorum_integer = function (key) {
    }
}