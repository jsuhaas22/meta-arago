SUMMARY = "Application for routing native applications via websockets"
HOMEPAGE = "http://websocketd.com/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=a14d7befdbee1290ac5c472cd85d66f2"

inherit go-mod

GO_IMPORT = "github.com/joewalnes/websocketd"

export GOPROXY = "https://proxy.golang.org,direct"

SRC_URI = "git://${GO_IMPORT};protocol=https;branch=master;destsuffix=${GO_SRCURI_DESTSUFFIX}"
SRCREV = "035c18cc3e6962dabd5ea2ad8845260726a4a99e"

# bitbake only exports proxy variables during fetching, but go handles
# module fetching on its own during compile and needs proxy settings
# along with network access for the task
export http_proxy
export https_proxy
do_compile[network] = "1"

# Development package contains all the examples in different languages
INSANE_SKIP:${PN}-dev = "file-rdeps"
