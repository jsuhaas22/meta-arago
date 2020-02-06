PACKAGECONFIG[kms] = "-Dbackend-drm=true,-Dbackend-drm=false,drm udev virtual/libgbm mtdev"

PR_append = ".arago1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RDEPENDS_${PN} += "weston-conf"

#SRC_URI += " \
#        file://0002-weston-Allow-visual_id-to-be-0.patch \
#        file://0003-weston-Fix-virtual-keyboard-display-issue-for-QT5-ap.patch \
#        file://0004-weston-Fix-touch-screen-crash-issue.patch \
#        file://0005-weston-drm-fix-dual-display-issue.patch \
#"

INHIBIT_PACKAGE_STRIP = "1"
